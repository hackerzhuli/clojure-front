param (
    [string]$script= "",
	[string]$arg= ""
 )
java -cp "D:\languages\clojure\clojure-1.6.0\clojure-1.6.0.jar;cljlib;" clojure.main $script $arg