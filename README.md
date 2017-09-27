# elastic-java-client
a java client for working with elasticsearch

I use jsoup library to send http request but with some change to can add request body to all methods like http get
## dependencies
* [java-json](https://github.com/stleary/JSON-java)
* [commons-codec](https://commons.apache.org/proper/commons-codec/download_codec.cgi) - only for using x-pack and authorizate

## usage
1- add jar file in your classpath  
2- create an object from Elastic class like:
```
//without authorization
Elastic elastic = new elastic("localhost", 9200);
//with authorization
Elastic elastic = new elastic("localhost", 9200, "elastic", "changeme");
```
3- call customQurey function like
```
JSONObject document = new JSONObject();
document.put("text", "some text");
String result = elastic.customQuery("index/type", document.toString(), Method.POST);
```
## to do
1- create a function to add or update document  
2- create a function for create index and type  
3- create a function for create simple query dsl  
4- create a function for searching  
