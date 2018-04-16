CREATE TABLE job_trigger (
  id              BIGINT AUTO_INCREMENT PRIMARY KEY NOT NULL,
  job_name        VARCHAR(32)                       NOT NULL,
  job_group       VARCHAR(32)                       NOT NULL,
  job_className   VARCHAR(32)                       NOT NULL,
  trigger_name    VARCHAR(32)                       NOT NULL,
  trigger_group   VARCHAR(32)                       NOT NULL,
  repeat_interval BIGINT                            NOT NULL,
  times_triggered BIGINT                            NOT NULL,
  cron_expression VARCHAR(32)                       NOT NULL,
  time_zone_id     VARCHAR(32)                       NOT NULL,
);

COMMIT;
