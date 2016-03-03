databaseChangeLog = {

	changeSet(author: "amonetta (generated)", id: "1456400970725-1") {
		modifyDataType(columnName: "id", newDataType: "bigint", tableName: "VehicleModelYear")
	}

	changeSet(author: "amonetta (generated)", id: "1456400970725-2") {
		addNotNullConstraint(columnDataType: "varchar(50)", columnName: "make", tableName: "VehicleModelYear")
	}

	changeSet(author: "amonetta (generated)", id: "1456400970725-3") {
		dropNotNullConstraint(columnDataType: "bigint", columnName: "owner_id", tableName: "VehicleModelYear")
	}

	changeSet(author: "amonetta (generated)", id: "1456400970725-4") {
		dropNotNullConstraint(columnDataType: "varchar(255)", columnName: "plate", tableName: "VehicleModelYear")
	}

	changeSet(author: "amonetta (generated)", id: "1456400970725-6") {
		dropIndex(indexName: "I_VehicleModelYear_make", tableName: "VehicleModelYear")
	}

	changeSet(author: "amonetta (generated)", id: "1456400970725-7") {
		dropIndex(indexName: "I_VehicleModelYear_model", tableName: "VehicleModelYear")
	}

	changeSet(author: "amonetta (generated)", id: "1456400970725-8") {
		dropIndex(indexName: "I_VehicleModelYear_year", tableName: "VehicleModelYear")
	}

	changeSet(author: "amonetta (generated)", id: "1456400970725-9") {
		dropIndex(indexName: "U_VehicleModelYear_year_make_model", tableName: "VehicleModelYear")
	}

	changeSet(author: "amonetta (generated)", id: "1456400970725-10") {
		createIndex(indexName: "FKFB2D615A4BF11821", tableName: "VehicleModelYear") {
			column(name: "owner_id")
		}
	}

	changeSet(author: "amonetta (generated)", id: "1456400970725-5") {
		addForeignKeyConstraint(baseColumnNames: "owner_id", baseTableName: "VehicleModelYear", constraintName: "FKFB2D615A4BF11821", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "Person", referencesUniqueColumn: "false")
	}
}
