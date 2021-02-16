use mysql;
CREATE USER 'application'@'%' IDENTIFIED BY 'application';
grant select,insert,update,delete,create,drop,index,alter on short-url.* to 'application'@'%';
flush privileges;