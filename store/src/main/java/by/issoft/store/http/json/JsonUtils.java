package by.issoft.store.http.json;

import by.issoft.domain.Category;
import by.issoft.domain.Product;
import by.issoft.domain.categories.CategoryType;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class JsonUtils {

    public static String buildStoreJson(List<Category> categoryList) {
        JSONObject jsonObject = new JSONObject();
        for (Category category : categoryList) {
            JSONObject jsonCategoryObject = new JSONObject();
            jsonCategoryObject.put("category", category.getCategoryId());
            jsonCategoryObject.accumulate("category", category.getNameCategory());
            for (Product product : category.getProductList()) {
                JSONObject jsonProductObject = new JSONObject();
                jsonProductObject.put("product", product.getProductId());
                jsonProductObject.accumulate("product", product.getCategoryId());
                jsonProductObject.append("product", product.getName());
                jsonProductObject.append("product", product.getPrice());
                jsonProductObject.append("product", product.getRate());
                jsonCategoryObject.accumulate("category", jsonProductObject);
            }
            jsonObject.accumulate("category", jsonCategoryObject);
        }
        return jsonObject.toString();
    }

    public static List<Category> parseStoreJson(String resultJson) {
        List<Category> categoryList = new ArrayList<>();
        JSONObject categoryJsonObject = new JSONObject(resultJson);
        JSONArray allCategoryArray = (JSONArray) categoryJsonObject.get("category");
        Iterator allCategoriesItr = allCategoryArray.iterator();
        while (allCategoriesItr.hasNext()) {
            JSONObject categoryObj = (JSONObject) allCategoriesItr.next();
            JSONArray categoryJson = categoryObj.getJSONArray("category");
            Category category = new Category(CategoryType.valueOf(categoryJson.get(1).toString().toUpperCase()));
            for(int i = 2; i < categoryJson.length(); i++) {
                JSONObject productObj = (JSONObject) categoryJson.get(i);
                JSONArray productArray = productObj.getJSONArray("product");
                Product product = Product.newBuilder()
                        .setProductId(productArray.getInt(0))
                        .setCategoryId(productArray.getInt(1))
                        .setName(productArray.get(2).toString())
                        .setPrice(productArray.getDouble(3))
                        .setRate(productArray.getDouble(4))
                        .build();
                category.setItemProduct(product);
            }
            categoryList.add(category);
        }
        return categoryList;
    }
}
