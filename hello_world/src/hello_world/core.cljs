(ns hello-world.core
  (:require-macros [hello-world.macros :as ng]))

(enable-console-print!)
(println "Hello world!")
(println (.guid js/jshelper))

  
;英文字符算1个字节
;其他字符算3个字节
;因为其他字符，一般情况是中文，在utf-8（数据库的存储格式）中，是3个字节
(defn str-byte-length [s]
  (reduce +
    (map #(if (> (.charCodeAt s %) 0xff) 3 1)
      (range (.-length s)))))
(println "byte-length" (str-byte-length "english 中文结合"))

;return an error string if something is wrong
;return nil if no error
(defn check-length [s min-length max-length]
  (let [len (str-byte-length s)]
    (if (and (not (nil? min-length)) (< len  min-length))
      "太短了"
      (if (and (not (nil? max-length)) (> len max-length))
        "太长了"
        nil))))
  
(println (check-length "hhjkhjkh" 2 10))
(ng/module "magic" [])
(ng/directive "magic" "magicTextInput"
{
  :scope {:data "=" :model "="}
  :template 
"<div>
  <span class=\"field-label\">
    {{label()}}
  </span>
  <input type=\"text\" ng-model=\"model\">
  <br>
  <span class=\"field-error\">
    {{error()}}
  </span>
</div>"
:controller
(fn [$scope]
(let [data (aget $scope "data")]
  (aset $scope "label" (fn [] (get data :label)))
  (aset $scope "hehe" (fn[] "hehe"))
  (aset $scope "error"
    (fn []
      (let 
        [s (aget $scope "model")
        checks (list 
        #(check-length % (get data :min-length) (get data :max-length)))]
        (first (filter #(not (nil? %)) (map #(% s) checks)))
      )))

))})  



(ng/module "hello" ["magic"])

(ng/controller "hello" "MainController"
  (fn [$scope] 
    (aset $scope "text" "This is soooo simplllle.")
    (aset $scope "name" "")
    (aset $scope "name_input" {:label "姓名" :max-length 6})
  ))
	

(ng/value "hello" "hiphop" {:a 10})
 
(ng/directive "hello" "rock"
{ 
  :scope {:name "="}
  :template "<div>{{name}} rocks.</div>"
  :controller 
    (fn [$scope, hiphop]
      (println (clj->js (list 1 2 3) ))
      (println (map #(* % 2) (array 1 2 3 4)))
	  (println (aget hiphop "a")))
})



