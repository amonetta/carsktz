databaseChangeLog = {

	changeSet(author: "amonetta (generated)", id: "1456395311377-1") {
		createTable(tableName: "VehicleModelYear") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "VehicleModelYPK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "make", type: "varchar(50)") {
				constraints(nullable: "false")
			}

			column(name: "model", type: "varchar(50)") {
				constraints(nullable: "false")
			}

			column(name: "year", type: "integer") {
				constraints(nullable: "false")
			}
		}
	}

	include file: 'changelog.1.groovy'

	include file: 'changelog.2.groovy'

	include file: 'changelog.3.groovy'

	include file: 'changelog.4.groovy'
}
