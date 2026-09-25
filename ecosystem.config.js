module.exports = {
  apps: [
    {
      name: "product-service",
      script: "java",
      args: "-jar product-service.jar",
      cwd: "/opt/cloudmart/product-service",
      env: {
        SERVER_PORT: "8081",
        CONFIG_SERVER_URL: "http://localhost:8888",
        EUREKA_SERVER_URL: "http://localhost:8761/eureka",
        // --- Fill these in with your actual Cloud SQL details at deploy time ---
        DB_URL: "jdbc:mysql://<CLOUD_SQL_PRIVATE_IP>:3306/productdb",
        DB_USERNAME: "user",
        DB_PASSWORD: "CHANGE_ME",
        GCS_BUCKET_NAME: "cloudmart-product-images"
      },
      autorestart: true,
      max_restarts: 10,
      min_uptime: "10s",
      restart_delay: 3000,
      out_file: "/var/log/pm2/product-service-out.log",
      error_file: "/var/log/pm2/product-service-error.log",
      log_date_format: "YYYY-MM-DD HH:mm:ss"
    }
  ]
};
