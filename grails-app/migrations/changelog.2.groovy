databaseChangeLog = {

    changeSet(author: "amonetta (generated)", id: "1456403968523-1") {
        modifyDataType(columnName: "id", newDataType: "bigint", tableName: "VehicleModelYear")
    }

    changeSet(author: "amonetta (generated)", id: "1456403968523-2") {
        dropIndex(indexName: "I_VehicleModelYear_make", tableName: "VehicleModelYear")
    }

    changeSet(author: "amonetta (generated)", id: "1456403968523-3") {
        dropIndex(indexName: "I_VehicleModelYear_model", tableName: "VehicleModelYear")
    }

    changeSet(author: "amonetta (generated)", id: "1456403968523-4") {
        dropIndex(indexName: "I_VehicleModelYear_year", tableName: "VehicleModelYear")
    }

    changeSet(author: "amonetta (generated)", id: "1456403968523-5") {
        dropIndex(indexName: "U_VehicleModelYear_year_make_model", tableName: "VehicleModelYear")
    }
}
