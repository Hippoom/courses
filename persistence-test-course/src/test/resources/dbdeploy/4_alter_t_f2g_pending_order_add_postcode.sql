alter table t_f2g_pending_order
  add column post_code varchar2(100);
  
--//@UNDO
alter table t_f2g_pending_order
  drop column post_code;
