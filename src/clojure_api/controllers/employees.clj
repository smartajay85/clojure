(ns clojure-api.controllers.employees
  (:require [clojure-api.models.employees :as emp]
            [compojure.core :refer [defroutes GET POST PUT DELETE]]
            [compojure.coercions :refer [as-int]]
            [ring.util.response :refer [response]]))

(defn getAllEmployee [] (emp/getAllEmployee))

(defn getEmployee [id] (emp/getEmployee id))

(defn createEmployee [params] (emp/createEmployee params))

(defn updateEmployee [id params] (emp/updateEmployee id params))

(defn deleteEmployee [id] (emp/deleteEmployee id))

(defroutes
  routes
  (GET "/employees" [] (response (getAllEmployee)))

  (GET "/employee/:employeeId{[0-9]+}"
       [employeeId :<< as-int]
       (response (getEmployee employeeId)))
  
  (POST "/employee/create" request (response (createEmployee (request :body))))

  (PUT "/employee/update/:employeeId{[0-9]+}"
       [employeeId :<< as-int :as request]
       (response (updateEmployee employeeId (request :body))))
  
  (DELETE "/employee/delete/:employeeId{[0-9]+}"
          [employeeId :<< as-int]
          (response (deleteEmployee employeeId))))
  