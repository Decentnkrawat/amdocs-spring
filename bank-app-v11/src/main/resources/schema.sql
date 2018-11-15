
create schema my_bank;



create table my_bank.ACCOUNTS (
    num varchar(255) not null,
    balance double not null,
    type varchar(255),
    primary key (num)
);

create table my_bank.TXNS (
    id integer generated by default as identity,
    amount double not null,
    txn_date timestamp,
    type varchar(255),
    acc_num varchar(255),
    primary key (id),
    foreign key (acc_num) references my_bank.ACCOUNTS(num)
);