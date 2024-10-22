-- bazines patikros
SELECT table_schema, table_name, table_type FROM INFORMATION_SCHEMA.tables WHERE table_schema = 'tagu8226';
SELECT table_schema, table_name, constraint_name FROM INFORMATION_SCHEMA.Key_column_usage WHERE table_schema = 'tagu8226';

SELECT  event_object_table AS table_name, trigger_name
FROM information_schema.triggers
GROUP BY table_name, trigger_name 
ORDER BY table_name, trigger_name;