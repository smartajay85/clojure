(ns clojure-api.models.departments
  (:require [clojure.java.jdbc :as sql]
            [clojure-api.db :as db])
  (:gen-class))

(defn createDepartment [params]
  (try (let [name (:name params)
             result (sql/insert! db/db-config :cj_departments {:name name})]
         (if result
           (do 
             (str "Departments created successfully"))
           (do 
             (str "OOPS-- some problem occured department not created"))))
       (catch java.sql.SQLException _ (str "An SQL Query error occured"))
       (catch Exception e (str "An error occured:" (.getMessage e)))))

(defn getAllDepartment
  []
  (try (into []
             (sql/query
               db/db-config
               ["select * from cj_departments order by id desc limit 1000"]))
       (catch java.sql.SQLException _ (str "An SQL Query error occured"))
       (catch Exception e (str "An error occured:" (.getMessage e)))))

(defn getDepartment
  [id]
  (try (first (sql/query db/db-config
                         ["select * from cj_departments where id = ?" id]))
       (catch java.sql.SQLException _ (str "An SQL Query error occured"))
       (catch Exception e (str "An error occured:" (.getMessage e)))))

(defn updateDepartment
  [id params]
  (try (sql/update! db/db-config
                    :cj_departments
                    {:name (:name params)}
                    ["id = ?" id])
       (getDepartment id)
       (catch java.sql.SQLException _ (str "An SQL error occured "))
       (catch Exception e (str "An error occured :" (.getMessage e)))))

(defn deleteDepartment
  [id]
  (try (sql/delete! db/db-config :cj_departments ["id = ?" id])
       []
       (str "Deleted Successfully Department ID " id)
       (catch java.sql.SQLException _ (str "An SQL error occured "))
       (catch Exception e (str "An error occured :" (.getMessage e)))))