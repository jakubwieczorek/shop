create user extuser identified by les6sari default tablespace tablespace_extuser;
grant connect, resource, IMP_FULL_DATABASE, EXP_FULL_DATABASE to extuser;
GRANT UNLIMITED TABLESPACE TO extuser;
