package kotlink.sp

import com.google.devtools.ksp.processing.Dependencies
import com.google.devtools.ksp.processing.Resolver
import com.google.devtools.ksp.processing.SymbolProcessor
import com.google.devtools.ksp.processing.SymbolProcessorEnvironment
import com.google.devtools.ksp.symbol.KSAnnotated
import com.google.devtools.ksp.symbol.KSClassDeclaration


class MyAnnotationProcessor(
    private val environment: SymbolProcessorEnvironment
) : SymbolProcessor {

    private val logger = environment.logger

    override fun process(resolver: Resolver): List<KSAnnotated> {
        val symbols = resolver.getSymbolsWithAnnotation("kotlink.sp.MyAnnotation")

        // 애너테이션이 달린 클래스에 대한 코드 생성
        symbols.filterIsInstance<KSClassDeclaration>().forEach { classDeclaration ->
            val annotation =
                classDeclaration.annotations.find { it.annotationType.resolve().declaration.qualifiedName?.asString() == "kotlink.sp.MyAnnotation" }
            val value = annotation?.arguments?.firstOrNull()?.value as? String ?: "defaultValue"

            // 생성할 소스 파일 이름은 class 이름 + ".Generated.kt"
            val generatedFileName = "${classDeclaration.simpleName.asString()}Generated"
            val generatedCode = """
                package ${classDeclaration.packageName.asString()}
                
                class ${classDeclaration.simpleName.asString()}Generated {
                    val annotationValue: String = "$value"
                }
            """.trimIndent()

            // codeGenerator 사용
            val containingFile = classDeclaration.containingFile
            if (containingFile != null) {
                // Dependencies 객체 생성 (aggregating은 false, KSFile을 의존성으로 설정)
                val dependencies = Dependencies(
                    aggregating = false,  // 개별 파일 의존성으로 설정
                    containingFile       // 의존성에 사용할 KSFile을 전달
                )

                // 파일 생성
                val file = environment.codeGenerator.createNewFile(
                    dependencies = dependencies,  // Dependencies 객체를 전달
                    packageName = classDeclaration.packageName.asString(),
                    fileName = generatedFileName
                )
                file.write(generatedCode.toByteArray())
            } else {
                logger.error("No containing file for ${classDeclaration.simpleName.asString()}")
            }
        }

        return emptyList() // 처리한 심볼 반환 (없으면 emptyList)
    }
}