# notification_system
Kafka based notification system 
Prequisite:-


Install Kafka and Zookeeper

for mac-
brew install kafka
-----------------------------------------------------------------------------------------

Start zookeeper and Kafka
==========================================================================================

zookeeper-server-start /usr/local/etc/kafka/zookeeper.properties

kafka-server-start /usr/local/etc/kafka/server.properties


-------------------------------------------------------------------------------------------

Create 3 Topic with partitions 3 and replication-factor 1 (because we have local machine so cant replicate more than 1)

==========================================================================================

bin/kafka-topics --create --zookeeper localhost:2181 --topic email-topic1 --partitions 3 --replication-factor 1

bin/kafka-topics --create --zookeeper localhost:2181 --topic push-topic1  --partitions 3 --replication-factor 1
  
bin/kafka-topics --create --zookeeper localhost:2181 --topic mobile-topic1 --partitions 3 --replication-factor 1
=========================================================

How to Run Application:- unzip project

1:-Import project as maven project -notification-system-tsystem

2- Run NotificationRun.java by right click on file and run as java application 
path of above file:-/notification-system-tsystem/src/main/java/com/app/NotificationRun.java

3-See console for consumer
=========================================================
Architecture:-

Database:h2 in memory database coz its just poc otherwise we can user mysql or any other sql database;
Springboot:-for api and other logical handling
Kafka: For handeling and request make it parrallel also for 100% deliver and 99.9% uptime.
Security:-Used Md5 based authkey -for make it unique use client+currenttimestamp
JAVA-8

=========================================================
FYI

1-I have not created any service who send sms ,push or email --because thats not a blocker for us.
2.Kafka help us to filter data base on client and type(email,push,sms) --auto load balance.
3-we can scale our cunsumer for email ans sms and push -need to add more consumer machined and point to same consumer group.
4-Not implemented unsubscribe
5-Not implemented delete client
6-Assuming client is always uinque
7-Not implement error code.
8-Not commented much in code due lack of time.

=========================================================
API - for every start you need to start with api 2 than api 3 coz in memory database would reset after application restart.

1:Pack Info :- to get pack details.

note -dont need any auth coz it does not impact our database anybody can query our product info.

URL:-get
http://127.0.0.1:8080/app/packInfo

output 
[{"packName":"Send Email - Daily Limit 1 M","packInfo":"$49","id":1},{"packName":"Send Email and SMS  - Total Daily Limit 10 M","packInfo":"$99","id":2},{"packName":"SSend Email and SMS and PUSH  - Unlimited","packInfo":"$500","id":3}]

status code :-200

-------------------------------------------------------------------------------------------
2:Subscription API :- Subscribe pack which give you auth key to use upload data for processing

No auth coz in realtime to access this you need to payment gateway.

URL:-POST
http://127.0.0.1:8080/app/packInfo


PARAM:-
client:neeraj  -- use can any client
packId:1.  -- use 1 ,2,3 pack id

Header:
Content-Type:application/json


output :--provide auth key to upload'
status code :-200

[Kindly use mentioned authkey for api calls 80cfeab699cab9199a8550fc20f562a9]

-------------------------------------------------------------------------------------------

3:Uplaod API :- Subscribe pack which give you auth key to use upload data for processing

No auth coz in realtime to access this you need to payment gateway.

URL:-POST
http://127.0.0.1:8080/app/user/upload?client=neeraj11&authkey=e882bb7b13c9fdab30b2ae854764d333

PARAM:-  
client:neeraj11 // use same client for which subscribed.
authkey:e882bb7b13c9fdab30b2ae854764d333 //auth key generated by api 2:Subscription API for client

Header:
Content-Type:application/json

Body:- sample
[
    {
        "type": "email",
        "content": "Abhijeet",
        "type_value": "neeraj.goel@gmail.com"
    },
    {
         "type": "mobile",
        "content": "Neeraj",
        "type_value": "7838872866"
    },
        {
        "type": "push",
        "content": "Shahs",
        "type_value": "Android-device-9111d"
        }
    ]

output : empty
status code :-102
-------------------------------------------------------------------------------------------

Sample curl call import in postman and go in paste raw section
After import upload api -change authkey obtained from subscription api


1:Pack Info :- to get pack details.

curl -X GET \
  http://127.0.0.1:8080/app/packInfo \
  -H 'Accept: */*' \
  -H 'Cache-Control: no-cache' \
  -H 'Connection: keep-alive' \
  -H 'Host: 127.0.0.1:8080' \

=========================================================

2:Subscription API :- Subscribe pack which give you auth key to use upload data for processing

curl -X POST \
  'http://127.0.0.1:8080/app/user/subscribe/?client=neeraj2&packId=3' \
  -H 'Accept: */*' \
  -H 'Cache-Control: no-cache' \
  -H 'Connection: keep-alive' \
  -H 'Content-Type: application/json' \
  -H 'Host: 127.0.0.1:8080' \
=========================================================

3:Uplaod API :- Subscribe pack which give you auth key to use upload data for processing 

curl -X POST \
  'http://127.0.0.1:8080/app/user/upload?client=neeraj11&authkey=e882bb7b13c9fdab30b2ae854764d333' \
  -H 'Content-Type: application/json' \
  -H 'cache-control: no-cache' \
  -d '[
    {
        "type": "email",
        "content": "Abhijeet",
        "type_value": "neeraj.goel@gmail.com"
    },
    {
        "type": "mobile",
        "content": "Neeraj",
        "type_value": "7838872866"
    },
    {
        "type": "push",
        "content": "Shahs",
        "type_value": "Android-device-9111d"
    }
]'
-------------------------------------------------------------------------------------------
How to access H2 database after application run.

1.open
http://localhost:8080/h2/

2.Click on connect

3 new ui open 
on right side all table are displayed.you can simple use mysql query
Client_auth
Data_Counter
pack_info
subscription
pack_default_limit

//deault date input path
/notification-system-tsystem/src/main/resources/data.sql



