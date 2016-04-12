databaseChangeLog = {

    changeSet(author: "amonetta (generated)", id: "1459943118317-1") {
        modifyDataType(columnName: "id", newDataType: "bigint", tableName: "VehicleModelYear")
    }

    changeSet(author: "amonetta (generated)", id: "1459943118317-2") {
        addNotNullConstraint(columnDataType: "varchar(255)", columnName: "plate", tableName: "VehicleModelYear")
    }

    changeSet(author: "amonetta (generated)", id: "1459943118317-3") {
        dropForeignKeyConstraint(baseTableName: "VehicleModelYear", baseTableSchemaName: "carsktzdb.dev", constraintName: "FKFB2D615ABFCF75DF")
    }

    changeSet(author: "amonetta (generated)", id: "1459943118317-4") {
        dropIndex(indexName: "I_VehicleModelYear_make", tableName: "VehicleModelYear")
    }

    changeSet(author: "amonetta (generated)", id: "1459943118317-5") {
        dropIndex(indexName: "I_VehicleModelYear_model", tableName: "VehicleModelYear")
    }

    changeSet(author: "amonetta (generated)", id: "1459943118317-6") {
        dropIndex(indexName: "I_VehicleModelYear_year", tableName: "VehicleModelYear")
    }

    changeSet(author: "amonetta (generated)", id: "1459943118317-7") {
        dropIndex(indexName: "U_VehicleModelYear_year_make_model", tableName: "VehicleModelYear")
    }

    changeSet(author: "amonetta (generated)", id: "1459943118317-8") {
        dropTable(tableName: "owner")
    }
}
