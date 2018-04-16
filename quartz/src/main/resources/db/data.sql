INSERT INTO job_trigger (
  job_name,
  job_group,
  job_className,
  trigger_name,
  trigger_group,
  repeat_interval,
  times_triggered,
  cron_expression,
  time_zone_id)
VALUES
  ('hello', 'test', 'com.example.demo.job.HelloJob', 'test', 'test', 0, 0, '0/10 * * * * ? *', '北京时间');