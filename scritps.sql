create table t_api_imovel (
      cd_imovel number(9,0) primary key,
      ds_imovel varchar(255) not null,
      nr_dimensao number(9,2),
      vl_imovel number (9,2) not null
);

create sequence sq_t_api_imovel start with 1 increment by 1 nocache;

create table t_api_tipo_imovel (
        cd_tipo number(9,0) primary key,
        nm_tipo varchar(60) not null,
        dt_cadastro date
);

create sequence sq_t_api_tipo_imovel start with 1 increment by 1 nocache;

--Adicionar a FK na tabela de imovel
alter table t_api_imovel add cd_tipo number(9,0);
alter table t_api_imovel add CONSTRAINT fk_api_tipo FOREIGN key (cd_tipo) references t_api_tipo_imovel(cd_tipo);