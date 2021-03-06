
/*
getMetaMethod存在的二义性
*/
class Logger{
    def info(String template, Object...params){
        println "$template, pararms:${params}"
    }
    def info(String msg){
        println "${msg}"
    }
    def info(String msg, Object para1){
        println "${msg},para1:${para1}"
    }
    def info(String msg, Object para1, Object para){
        println "${msg},para1:${para1}"
    }
}

def logger=new Logger()

def method = Logger.metaClass.getMetaMethod("info",String.class, Object[].class)
println method

method = Logger.metaClass.getMetaMethod("info",[String.class, Object[].class] as Object[])
println method

/*
这里获取到的method是：def info(String msg, Object para1, Object para)
def info(String template, Object...params)被“覆盖”了
*/
method = Logger.metaClass.getMetaMethod("info",["",1,2] as Object[])
println method
/*
这里获取到的method是：def info(String msg, Object para1)
def info(String template, Object...params)被“覆盖”了
*/
method = Logger.metaClass.getMetaMethod("info",["",1] as Object[])
println method



/*
出现了wrong number of arguments异常
*/
method = Logger.metaClass.getMetaMethod("info",["",null,2] as Object[])
println method
try{
    println method.invoke(logger,["call logger", 1, 2]as Object[])
}catch(IllegalArgumentException e){
    println "method.invoke(logger,['call logger', null, 2]as Object[]) failed:${e}"
}
