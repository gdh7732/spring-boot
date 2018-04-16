CREATE TABLE job_trigger (
  id              BIGINT AUTO_INCREMENT PRIMARY KEY NOT NULL,
  job_name        VARCHAR(32),
  job_group       VARCHAR(32)                       NOT NULL,
  job_className   VARCHAR(32)                       NOT NULL,
  trigger_name    VARCHAR(32),
  trigger_group   VARCHAR(32),
  repeat_interval BIGINT,
  times_triggered BIGINT,
  cron_expression VARCHAR(32)                       NOT NULL,
  time_zone_id    VARCHAR(32),
);

COMMIT;
