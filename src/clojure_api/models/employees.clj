(ns clojure-api.models.employees
  (:require [clojure.java.jdbc :as sql]
            [clojure-api.db :as db])
  (:gen-class))

(defn createEmployee [params]
  (try (let [name (:name params)
             phone (:phone params)
             email (:email params)
             dept_id (:dept_id params)
             result
               (sql/insert!
                 db/db-config
                 :cj_employees
                 {:name name, :phone phone, :email email, :dept_id dept_id})]
         (if result
           (do 
             (str "Employee created successfully"))
           (do 
             (str "OOPS-- some problem occured employee not created"))))
       (catch java.sql.SQLException _ (str "An SQL Query error occured"))
       (catch Exception e (str "An error occured:" (.getMessage e)))))

(defn getAllEmployee []
  (try
  (into []
        (sql/query db/db-config ["select * from cj_employees order by id desc limit 1000"]))
  
       (catch java.sql.SQLException _ (str "An SQL Query error occured"))
       (catch Exception e (str "An error occured:" (.getMessage e)))))

(defn getEmployee [id]
  (try (first (sql/query db/db-config ["select * from cj_employees where id = ?" id]))
       (catch java.sql.SQLException _ (str "An SQL Query error occured"))
       (catch Exception e (str "An error occured:" (.getMessage e))))
  )

(defn updateEmployee [id params]
  (try (sql/update! db/db-config
                    :cj_employees
                    {:name (:name params),
                     :phone (:phone params),
                     :email (:email params),
                     :dept_id (:dept_id params)}
                    ["id = ?" id])
       (getEmployee id)
       (catch java.sql.SQLException _ (str "An SQL error occured "))
       (catch Exception e (str "An error occured :" (.getMessage e)))))

(defn deleteEmployee [id]
  (try (sql/delete! db/db-config :cj_employees ["id = ?" id])
         [] 
         (str "Deleted Successfully Employee ID " id)
       (catch java.sql.SQLException _ (str "An SQL error occured "))
       (catch Exception e (str "An error occured :" (.getMessage e)))))