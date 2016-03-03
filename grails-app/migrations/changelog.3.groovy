databaseChangeLog = {

    changeSet(author: "amonetta (generated)", id: "1456445175449-1") {
        modifyDataType(columnName: "id", newDataType: "bigint", tableName: "VehicleModelYear")
    }

    changeSet(author: "amonetta (generated)", id: "1456445175449-2") {
        addNotNullConstraint(columnDataType: "varchar(255)", columnName: "plate", tableName: "VehicleModelYear")
    }

    changeSet(author: "amonetta (generated)", id: "1456445175449-3") {
        dropIndex(indexName: "I_VehicleModelYear_make", tableName: "VehicleModelYear")
    }

    changeSet(author: "amonetta (generated)", id: "1456445175449-4") {
        dropIndex(indexName: "I_VehicleModelYear_model", tableName: "VehicleModelYear")
    }

    changeSet(author: "amonetta (generated)", id: "1456445175449-5") {
        dropIndex(indexName: "I_VehicleModelYear_year", tableName: "VehicleModelYear")
    }

    changeSet(author: "amonetta (generated)", id: "1456445175449-6") {
        dropIndex(indexName: "U_VehicleModelYear_year_make_model", tableName: "VehicleModelYear")
    }
}
