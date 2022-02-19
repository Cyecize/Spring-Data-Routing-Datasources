
# Spring-Data-Routing-Datasources
Spring configuration for running multiple data sources using annotations.

Link to video on YouTube:
https://www.youtube.com/watch?v=ni9Nwhx-v-U

This project is about configuring Spring Data to work with multiple data sources and switch them using annotations.
There are three databases connected, 2 are MySql and one is MS SQL Server.

The routing is controlled by annotation @WithDatabase which hints the app which datasource to use for the next transaction.

Account entity is only present in MS SQL Server database, so there I have annotated the AccountRepository with @WithDatabase so that every communication with the DB 
can be re-routed to the proper database.

# Issue discovered after the video was recorded!
Check out this [commit](https://github.com/Cyecize/Spring-Data-Routing-Datasources/commit/184ebcd1b31944081e66000fc1ef8ea543224754)
The issue was that spring was using one dialect for both MySQL and MS SQL Server which caused syntax errors.
This commit fixes this issue by adding entity manager factory for each unique SQL Driver that was provided.
If you are planning to use this app with just one type of SQL Server, then this will not affect you!
