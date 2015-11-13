(ns hello-world.macros)

(defmacro module [name l]
  `(.module js/angular ~name (cljs.core/clj->js ~l)))
  
(defmacro directive [module name m]
  `(.directive (.module js/angular ~module) ~name
     (fn [] (cljs.core/clj->js ~m))))
	 
(defmacro value [module name m]
  `(.value (.module js/angular ~module) ~name (cljs.core/clj->js ~m)))
  
(defmacro controller [module name f]
  `(.controller (.module js/angular ~module) ~name ~f))
  