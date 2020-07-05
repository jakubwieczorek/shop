CREATE TABLESPACE "TABLESPACE_EXTUSER" DATAFILE 'tablespace_extuser_01.dbf'
  SIZE 10M
  AUTOEXTEND ON NEXT 5M MAXSIZE 500M
  LOGGING ONLINE PERMANENT BLOCKSIZE 8192
  EXTENT MANAGEMENT LOCAL AUTOALLOCATE DEFAULT
 NOCOMPRESS  SEGMENT SPACE MANAGEMENT AUTO;

CREATE TABLESPACE "TABLESPACE_TEST" DATAFILE './tablespace_testuser.dbf'
  SIZE 10M
  AUTOEXTEND ON NEXT 5M MAXSIZE 500M
  LOGGING ONLINE PERMANENT BLOCKSIZE 8192
  EXTENT MANAGEMENT LOCAL AUTOALLOCATE DEFAULT
 NOCOMPRESS  SEGMENT SPACE MANAGEMENT AUTO;
