param (
  $project
)

function Get-ScriptDirectory
{
  $Invocation = (Get-Variable MyInvocation -Scope 1).Value
  Split-Path $Invocation.MyCommand.Path
}

$abs_project_path =  (Join-Path (Get-ScriptDirectory) $project)
$out_str = [string]::Format("watching project {0}", $project)
echo $out_str
$original=$pwd.Path
cd $abs_project_path
$script = "watch.clj"

java -cp "d:\languages\cljs.jar;..\cljlib;..\manage;src" clojure.main $script
cd $original