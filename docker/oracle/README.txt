1) $ ./buildDockerImage.sh -v 18.4.0 -x
2) $ docker-compose up
3) $ docker exec -it oracle_db_18_4_xe_1 bash
4) $ su - oracle
5) $ . oraenv
   ORACLE_SID = [XE] ? XE

sqlplus ...
