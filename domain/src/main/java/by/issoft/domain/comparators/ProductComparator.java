package by.issoft.domain.comparators;

import by.issoft.domain.Product;
import by.issoft.domain.xmlParser.XmlParser;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

public class ProductComparator implements Comparator<Product> {

    public int compare(Product o1, Product o2) {
        return o1.getName().compareTo(o2.getName());
    }

    private static Comparator<Product> getComparator(String sortKey) {
        if(sortKey.equals("name")) {
            return Comparator.comparing(Product::getName);
        } else if (sortKey.equals("price")) {
            return Comparator.comparing(Product::getPrice);
        } else if (sortKey.equals("rate")) {
            return Comparator.comparing(Product::getRate);
        } else {
            throw new IllegalArgumentException();
        }
    }

    public static List<Product> sortProductList(List<Product> productList) {

        List<Product> plist = new ArrayList(productList);
        Map<String, String> sortingMap = XmlParser.getProductSorting();

        //parser returns something like: sortingMap.put("price","desc");

        for (Map.Entry<String, String> me : sortingMap.entrySet()) {
            if (me.getValue().equals(TypesOfSorting.ASC.name().toLowerCase())) {
                plist.sort(getComparator(me.getKey()));
            } else if (me.getValue().equals(TypesOfSorting.DESC.name().toLowerCase())) {
                plist.sort(getComparator(me.getKey()).reversed());
            }
        }
        return plist;
    }

    public static List<Product> sortProductByPrice(List<Product> productList) {
        productList.sort(getComparator("price").reversed());
        return productList;
    }
}
