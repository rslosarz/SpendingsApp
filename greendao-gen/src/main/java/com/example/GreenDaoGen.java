package com.example;

import de.greenrobot.daogenerator.DaoGenerator;
import de.greenrobot.daogenerator.Entity;
import de.greenrobot.daogenerator.Property;
import de.greenrobot.daogenerator.Schema;

public class GreenDaoGen {
    private static final String PROJECT_DIR = System.getProperty("user.dir");

    public static void main(String[] args) {
        Schema schema = new Schema(1, "slosar.srt.spendingsapp.DbModule");
        schema.enableKeepSectionsByDefault();
        addTables(schema);
        try {
            new DaoGenerator().generateAll(schema, PROJECT_DIR + "\\app\\src\\main\\java");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void addTables(final Schema schema) {
        Entity spending = addSpending(schema);
        Entity category = addCategory(schema);

        Property categoryId = spending.addLongProperty("categoryId").notNull().getProperty();
        spending.addToOne(category, categoryId, "category");
    }

    private static Entity addSpending(final Schema schema) {
        Entity spending = schema.addEntity("Spending");
        spending.addIdProperty().primaryKey().autoincrement();
        spending.addFloatProperty("value").notNull();
        spending.addStringProperty("title").notNull();
        spending.addDateProperty("date").notNull();
        return spending;
    }

    private static Entity addCategory(final Schema schema) {
        Entity user = schema.addEntity("Category");
        user.addIdProperty().primaryKey().autoincrement();
        user.addStringProperty("name").notNull();
        return user;
    }
}

