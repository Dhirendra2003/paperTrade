This is a Practice trading app made using JSP , JDBC in java . this dynamic web app is created on ECLIPSE IDE 
project PaperTrade uses Yahoo finance API for fetching realtime stock data. Link:  https://rapidapi.com/apidojo/api/yahoo-finance1

#technologies used:
1.Java -JSP & JDBC 
2.HTML , CSS & javascript 
3.MySQL database 
4.Rapid API- yahoo finance
5.Alpha vantage API - for news
6.Apache tomcat localhost - v10.1
7.AJAX

#To use this app follow these steps:
1.make new project in eclipse and copy all the files from master/ src 
2. configure apache tomcat server v.10 in your system 
3.create database named 'papertrade'
4.create 2 tables in DB - i)users - [id,uname,upwd,umail,umobile,money,stockHolded]
                          ii)trades - [id , userid,Ttype, price, quantity,stockname, totalvalue]

5.update link to database in java codes.
6.run app on server.

