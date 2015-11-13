(ns zhuli.path)

; return a list of names of files and folders in path
(defn listdir [path] (let [f (clojure.java.io/file path)] (map identity (.list f))))

(defn dir? [path] (let [f (clojure.java.io/file path)] (.isDirectory f)))

(defn join [p0 p1] (let [f (clojure.java.io/file p0 p1)] (.getPath f)))
(defn modified [path] (let [f (clojure.java.io/file path)] (.lastModified f)))

