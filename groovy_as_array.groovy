try{
    println ""
    println ([null, 1] as Object[]).length
}catch(java.lang.NullPointerException e){
    println "([null, 1] as Object[]).length failed:${e}"
}
def tmpArray=[null, 1] as Object[]
println tmpArray.length