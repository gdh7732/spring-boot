INSERT INTO job_trigger (
  job_name,
  job_group,
  job_className,
  trigger_name,
  trigger_group,
  is_delete,
  is_pause,
  cron_expression,
  time_zone_id)
VALUES
  ('hello', 'test', 'com.example.job.HelloJob', 'test', 'test', 0, 0, '0/10 * * * * ? *', '北京时间'),
  ('new', 'test', 'com.example.job.NewJob', 'test', 'test', 0, 0, '0/5 * * * * ? *', '北京时间');