terraform {
  required_providers {
    azurerm = {
      source  = "hashicorp/azurerm"
      version = "3.1.0"
    }
  }
}

provider "azurerm" {
  # subscription_id = "4f00f82f-e26d-40f2-b618-e60dfd3a3828" 
  # client_id = "d3b60a87-c29f-467d-a224-99cd270b8ce5"
  # client_secret = "D9Z7Q~ZRSPY1IYCuCOLcUdvtdpvEC1CzyGu~D"
  # tenant_id = "b887d5dc-bcc6-4f45-936a-70ebe1691150"
  features {}
}

resource "azurerm_resource_group" "rg" {
  name     = var.resource_group_name
  location = var.resource_group_location

  # tags = {
  #   environment = "preproduction"
  # }
}

# resource "azurerm_service_plan" "app_plan" {
#   name                = var.app_service_plan_name
#   resource_group_name = azurerm_resource_group.rg.name
#   location            = azurerm_resource_group.rg.location
#   sku_name            = "B1"
#   os_type             = "Linux"
# }

# resource "azurerm_app_service" "webapp" {
#   name                = var.app_service_name
#   location            = azurerm_resource_group.rg.location
#   resource_group_name = azurerm_resource_group.rg.name
#   app_service_plan_id = azurerm_service_plan.app_plan.id

#   site_config {
#     dotnet_framework_version = "v4.0"
#     scm_type                 = "LocalGit"
#   }

#   app_settings = {
#     "SOME_KEY" = "some-value"
#   }

#   connection_string {
#     name  = "Database"
#     type  = "SQLAzure"
#     value = "Server=tcp:azurerm_sql_server.sqldb.fully_qualified_domain_name Database=azurerm_sql_database.db.name;User ID=azurerm_sql_server.sqldb.administrator_login;Password=azurerm_sql_server.sqldb.administrator_login_password;Trusted_Connection=False;Encrypt=True;"
#   }
# }

resource "azurerm_postgresql_server" "offorsqldb" {
  name                         = var.sql_server_name
  resource_group_name          = azurerm_resource_group.rg.name
  location                     = azurerm_resource_group.rg.location
  version                      = "11"
  administrator_login          = var.sql_admin_login
  administrator_login_password = var.sql_admin_password
  sku_name = "B_Gen5_2"
  ssl_enforcement_enabled      = true
}

resource "azurerm_postgresql_database" "postdb" {
  name                = var.sql_database_name
  resource_group_name = azurerm_resource_group.rg.name
  server_name         = azurerm_postgresql_server.offorsqldb.name
  charset             = "UTF8"
  collation           = "English_United States.1252"
}

resource "azurerm_postgresql_firewall_rule" "primer_firewall" {
  name                = "primer_firewall"
  resource_group_name = azurerm_resource_group.rg.name
  server_name         = azurerm_postgresql_server.offorsqldb.name
  start_ip_address    = "24.155.196.56"
  end_ip_address      = "24.155.196.56"

  depends_on = [
    azurerm_postgresql_server.offorsqldb
  ]
}
