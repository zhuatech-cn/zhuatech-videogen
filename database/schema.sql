-- Copyright 2026 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/
CREATE DATABASE IF NOT EXISTS zhuatech_videogen DEFAULT CHARACTER SET utf8mb4;
USE zhuatech_videogen;
CREATE TABLE video_project (id BIGINT PRIMARY KEY AUTO_INCREMENT, project_name VARCHAR(120) NOT NULL, brief TEXT NOT NULL, duration_seconds INT NOT NULL, aspect_ratio VARCHAR(20) NOT NULL, visual_style VARCHAR(80) NOT NULL, status VARCHAR(30) NOT NULL, created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP);
CREATE TABLE render_job (id BIGINT PRIMARY KEY AUTO_INCREMENT, project_id BIGINT NOT NULL, provider_code VARCHAR(60) NOT NULL, provider_job_id VARCHAR(120), job_status VARCHAR(30) NOT NULL, progress INT NOT NULL DEFAULT 0, created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP, INDEX idx_render_project_status(project_id,job_status));
