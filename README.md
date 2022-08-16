# STOCKS BATCH JOB API

## Overview
The API has Batch Job which fetch the  Data of Stock IRCTC from Alpha Advantage API and store them in H2 database .
The API expose an endpoint through which We can fetch the stock data of last 30 days. 

## Guidelines for Developer

1. Clone this Project

2.Run the Application as Java Application.

## Guidelines for User

1.Hit the API using the path "/data" and Stcoks data for 30 days will be fetched.
    
```java
	   /data
```

2.Basic Authentication is required for all the Requests with credentials(admin/admin);
