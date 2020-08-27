package service;

import model.CustomerModel;
import model.SaleDetaillModel;
import model.SaleModel;
import model.SalesmanModel;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Stream;

public class FileService {

    private final String inDirectory = System.getProperty("user.home") + "/data/in";
    private final String outDirectory = System.getProperty("user.home") + "/data/out";

    List<SalesmanModel> salesmanModels = new ArrayList<>();
    List<CustomerModel> customerModels = new ArrayList<>();
    List<SaleModel> saleModels = new ArrayList<>();

    public void readAllFilesFromDirectory() throws IOException {

        if (!Files.exists(Paths.get(inDirectory))) {
            File file = new File(inDirectory);
            file.mkdir();
        }

        boolean firstTime = true;

        majorLoop:
        while (true) {

            List<SalesmanModel> salesmanModels = new ArrayList<>();
            List<CustomerModel> customerModels = new ArrayList<>();
            List<SaleModel> saleModels = new ArrayList<>();

            try (Stream<Path> paths = Files.walk(Paths.get(inDirectory))) {
                paths
                        .filter(Files::isRegularFile)
                        .forEach(file -> {
                                    try (Stream<String> stream = Files.lines(file)) {
                                        stream
                                                .forEach(x -> {
                                                    try {
                                                        if (!x.isEmpty() && !x.trim().equals("")) {
                                                            x = x.trim();
                                                            if (x.substring(0, 3).equals("001")) {
                                                                String[] fields = x.split("\\ç");

                                                                SalesmanModel salesmanModel = new SalesmanModel(
                                                                        fields[0], fields[1], fields[2], ((Float.parseFloat(fields[3])))
                                                                );

                                                                salesmanModels.add(salesmanModel);
                                                            } else if (x.substring(0, 3).equals("002")) {
                                                                String[] fields = x.split("\\ç");
                                                                CustomerModel customerModel = new CustomerModel(
                                                                        fields[0], fields[1], fields[2], fields[3]);

                                                                customerModels.add(customerModel);
                                                            } else if (x.substring(0, 3).equals("003")) {
                                                                SaleModel saleModel = new SaleModel();
                                                                SaleDetaillModel saleDetaillModel = new SaleDetaillModel();

                                                                String[] fields = x.split("\\ç");

                                                                String id = fields[0];
                                                                String salesId = fields[1];
                                                                String salesName = fields[3];

                                                                String salesDetails = fields[2]
                                                                        .replace("[", "")
                                                                        .replace("]", "");
                                                                String[] salesDetailsArray = salesDetails.split("\\,");


                                                                for (int i = 0; i < salesDetailsArray.length; i++) {
                                                                    saleDetaillModel = new SaleDetaillModel();
                                                                    String[] aux = salesDetailsArray[i].split("\\-");

                                                                    saleDetaillModel.setItemId(aux[0]);
                                                                    saleDetaillModel.setQuantityItem(Float.parseFloat(
                                                                            aux[1]
                                                                    ));
                                                                    saleDetaillModel.setPrice(Float.parseFloat(aux[2]));

                                                                    saleModel.getSaleDetais().add(saleDetaillModel);
                                                                }

                                                                saleModel.setId(id);
                                                                saleModel.setSaleId(salesId);
                                                                saleModel.setSalesman(salesName);
                                                                saleModels.add(saleModel);
                                                            }
                                                        }
                                                    } catch (Exception e) {
                                                        e.printStackTrace();
                                                    }
                                                });

                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }
                                }
                        );

                if (firstTime) {
                    this.salesmanModels = salesmanModels;
                    this.customerModels = customerModels;
                    this.saleModels = saleModels;
                } else {
                    boolean dataIsEquals = this.salesmanModels.equals(salesmanModels)
                            && this.customerModels.equals(customerModels)
                            && this.saleModels.equals(saleModels);
                    if (dataIsEquals) {

                        firstTime = false;
                        continue majorLoop;
                    } else {
                        this.salesmanModels = salesmanModels;
                        this.customerModels = customerModels;
                        this.saleModels = saleModels;
                    }
                }

                this.writeFileToDirectory(salesmanModels, customerModels, saleModels);

            }

            firstTime = false;
        }

    }

    private void writeFileToDirectory(List<SalesmanModel> salesmanModels, List<CustomerModel> customerModels, List<SaleModel> saleModels) throws IOException {
        String outFile = outDirectory + "/out.txt";

        if (!Files.exists(Paths.get(outDirectory))) {
            File file = new File(outDirectory);
            file.mkdir();
        }

        if (Files.notExists(Paths.get(outFile))) {
            File file = new File(outFile);
            file.createNewFile();
        }

        int customerQuantity = customerModels.size();
        int salesmanQuantity = salesmanModels.size();
        String moreExpensiveSaleId = getMoreExpensiveSaleId(saleModels);
        String worseSalesman = getWorseSaleman(saleModels);

        List<String> lines = Arrays.asList(
                "Total de Clientes: " + customerQuantity,
                "Total de Vendedores: " + salesmanQuantity,
                "Id da Venda mais cara: " + moreExpensiveSaleId,
                "Pior Vendedor: " + worseSalesman
        );
        Path file = Paths.get(outFile);
        Files.write(file, lines, StandardCharsets.UTF_8);
    }

    private String getMoreExpensiveSaleId(List<SaleModel> saleModels) {

        String moreExpensiveSaleId = "";
        float aux = 0;
        for (SaleModel sale : saleModels) {
            SaleDetaillModel detail = Collections.max(sale.getSaleDetais(), Comparator.comparing(SaleDetaillModel::getPriceOfSale));
            if (aux == 0) {
                aux = detail.getPriceOfSale();
                moreExpensiveSaleId = sale.getSaleId();
            } else if (detail.getPriceOfSale() > aux) {
                aux = detail.getPriceOfSale();
                moreExpensiveSaleId = sale.getSaleId();
            }
        }

        return moreExpensiveSaleId;
    }

    private String getWorseSaleman(List<SaleModel> saleModels) {

        String worseSalesman = "";
        double aux = 0;
        for (SaleModel sale : saleModels) {
            double minSum = sale.getSaleDetais().stream().mapToDouble(SaleDetaillModel::getPriceOfSale).sum();
            if (aux == 0) {
                aux = minSum;
                worseSalesman = sale.getSalesman();
            } else if (minSum < aux) {
                aux = minSum;
                worseSalesman = sale.getSalesman();
            }
        }

        return worseSalesman;
    }

}
