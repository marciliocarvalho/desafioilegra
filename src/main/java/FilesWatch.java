import model.CustomerModel;
import model.SaleDetaillModel;
import model.SaleModel;
import model.SalesmanModel;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.util.*;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Stream;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class FilesWatch {

    String inDirectory = System.getProperty("user.home") + "/data/in";

    public void watchDirectoryFiles() throws IOException, InterruptedException {

        File directory = new File(inDirectory);
        if (!directory.exists()) {
            directory.mkdir();
        }

        WatchService watchService
                = FileSystems.getDefault().newWatchService();

        Path path = Paths.get(inDirectory);

        path.register(
                watchService,
                StandardWatchEventKinds.ENTRY_CREATE,
                StandardWatchEventKinds.ENTRY_DELETE,
                StandardWatchEventKinds.ENTRY_MODIFY);

        WatchKey key;
        while ((key = watchService.take()) != null) {
            for (WatchEvent<?> event : key.pollEvents()) {

                System.out.println(
                        "Event kind:" + event.kind()
                                + ". File affected: " + event.context() + ".");
            }
            key.reset();
        }
    }

    public void readAllFilesFromDirectory() throws IOException {


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
//
                                                                Thread.sleep(1000);
                                                            }
                                                        }


//                                                System.out.print(customerModels);
//                                                Thread.sleep(2000);

                                                    } catch (Exception e) {
                                                        e.printStackTrace();
                                                    }
                                                });

                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }
//                                    System.out.println(saleModels.size());

                                    this.writeFileToDirectory(salesmanModels, customerModels, saleModels);
                                }
                        );

            }
        }

    }

    private void writeFileToDirectory(List<SalesmanModel> salesmanModels, List<CustomerModel> customerModels, List<SaleModel> saleModels) {
        int customerQuantity = customerModels.size();
        int salesmanQuantity = salesmanModels.size();
        String moreExpensiveSaleId = getMoreExpensiveSaleId(saleModels);
        String worseSalesman = getWorseSaleman(saleModels);


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
        float aux = 0;
        for (SaleModel sale : saleModels) {
            SaleDetaillModel detail = Collections.min(sale.getSaleDetais(), Comparator.comparing(SaleDetaillModel::getPriceOfSale));
            if (aux == 0) {
                aux = detail.getPriceOfSale();
                worseSalesman = sale.getSalesman();
            } else if (detail.getPriceOfSale() < aux) {
                aux = detail.getPriceOfSale();
                worseSalesman = sale.getSalesman();
            }
        }

        return worseSalesman;
    }

}
