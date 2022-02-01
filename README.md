# Spring-Data-Routing-Datasources
Spring configuration for running multiple data sources using annotations.

Link to video on YouTube:
https://www.youtube.com/watch?v=ni9Nwhx-v-U

This project is about configuring Spring Data to work with multiple data sources and switch them using annotations.
There are three databases connected, 2 are MySql and one is MS SQL Server.

The routing is controlled by annotation @WithDatabase which hints the app which datasource to use for the next transaction.

Account entity is only present in MS SQL Server database, so there I have annotated the AccountRepository with @WithDatabase so that every communication with the DB 
can be re-routed to the proper database.
