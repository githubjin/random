alter table random.rbac_user
   add column lang_key varchar(5) NOT NULL after activated,
   add column activation_key varchar(20) NULL after lang_key,
   add column reset_key varchar(20) NULL after activation_key,
   add column reset_date datetime NULL after reset_key;