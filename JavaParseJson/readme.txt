创建一个简单的mysql 数据库。

create database DTC;

create table catch_data_transfer_collection(
customer_id varchar(20)
,phone_coll varchar(1)
,severity_coll_H varchar(10)
,severity_coll_M varchar(10)
,severity_coll_L varchar(10)
,network_coll varchar(1)
,severity_net_H varchar(10)
,severity_net_M varchar(10)
,severity_net_L varchar(10)
);

insert into catch_data_transfer_collection(
customer_id
,phone_coll
,severity_coll_H
,severity_coll_M
,severity_coll_L
,network_coll
,severity_net_H
,severity_net_M
,severity_net_L
)
values(
'4022201990010235'
,'1'
,80
,0
,0
,1
,0
,50
,40
);