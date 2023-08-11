(ns clojure-api.controllers.departments
  (:require [clojure-api.models.departments :as dept]
            [compojure.core :refer [defroutes GET POST PUT DELETE]]
            [compojure.coercions :refer [as-int]]
            [ring.util.response :refer [response]]))

(defn getAllDepartment [] (dept/getAllDepartment))

(defn getDepartment [id] (dept/getDepartment id))

(defn createDepartment [params] (dept/createDepartment params))

(defn updateDepartment [id params] (dept/updateDepartment id params))

(defn deleteDepartment [id] (dept/deleteDepartment id))

(defroutes routes
           (GET "/departments" [] (response (getAllDepartment)))
           (GET "/department/:deptId{[0-9]+}"
                [deptId :<< as-int]
                (response (getDepartment deptId)))
           (POST "/department/create"
                 request
                 (response (createDepartment (request :body))))
           (PUT "/department/update/:deptId{[0-9]+}"
                [deptId :<< as-int :as request]
                (response (updateDepartment deptId (request :body))))
           (DELETE "/department/delete/:deptId{[0-9]+}"
                   [deptId :<< as-int]
                   (response (deleteDepartment deptId)))
  )
  