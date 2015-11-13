(ns zhuli.mass.core)

; 参数示例 [:margin-top "10px"], 是的，是一个vector
(defn one_pair [[k v]]
  (str (subs (str k) 1)
     ":" v ";"))

; (list {:a 2} {:b 2} {:c 2} (list 1 2 3) (list 1 3 4))
; => (list (list {:a 2} {:b 2} {:c 2}) (list (list 1 2 3) (list 1 3 4)))
(defn split_maps_and_lists [all]
  (if (empty? all)
    (list (list) (list))
    (let [rest_result (split_maps_and_lists (rest all))
          a (first all)]
      (if (map? a)
        (list (cons a (first rest_result)) (second rest_result))
        (list (first rest_result) (cons a (second rest_result))));endif
      )))
; example (selector "" ".page" {:margin "10px 20px 10px 20px" :color "black"} (list ".content" {:margin-top "20px"}))
(defn mass-compile [pre s & maps_and_lists]
  (let [full_s  (if (or (empty? pre) (empty? s)) (str pre s) (str pre " " s))
        [maps lists] (split_maps_and_lists maps_and_lists)]
    
    (str
      (if (empty? maps)
        ""
        (str
          full_s "{" 
          (clojure.string/join (map one_pair (seq (apply merge maps))))
          "}\n"))
      (clojure.string/join (map #(apply mass-compile (cons full_s %)) lists)))))

(defmacro main [locals & lists]
  `(def mass-code (let ~locals (list "" "" ~@lists))))

(def mass-code nil)

(defn compile-file [input-file output-file]
  (load-file input-file)
  ;(println mass-code)
  (with-open [out (clojure.java.io/writer output-file)]
    (.write out (apply mass-compile mass-code))))


