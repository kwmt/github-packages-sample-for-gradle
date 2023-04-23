package com.example.lint.checks.detectors

import com.android.tools.lint.client.api.UElementHandler
import com.android.tools.lint.detector.api.Category
import com.android.tools.lint.detector.api.Detector
import com.android.tools.lint.detector.api.Implementation
import com.android.tools.lint.detector.api.Issue
import com.android.tools.lint.detector.api.JavaContext
import com.android.tools.lint.detector.api.Scope
import com.android.tools.lint.detector.api.Severity
import org.jetbrains.uast.UElement
import org.jetbrains.uast.UImportStatement

class WrongBuildConfigImportDetector : Detector(), Detector.UastScanner {


    override fun getApplicableUastTypes(): List<Class<out UElement>> {
        return listOf(UImportStatement::class.java)
    }


    override fun createUastHandler(context: JavaContext) = InvalidImportHandler(context)

    class InvalidImportHandler(private val context: JavaContext) : UElementHandler() {

        override fun visitImportStatement(node: UImportStatement) {
            var importedPackageName = ""
            val classPackageName = context.uastFile?.packageName.toString()

            node.importReference?.let {
                importedPackageName = it.asSourceString()
            }
            val importedPackageSubFolders = importedPackageName.split(".")

            if (importedPackageSubFolders.last() == "BuildConfig") {

                val importedPackage =
                    importedPackageSubFolders.subList(0, importedPackageSubFolders.size - 1)
                        .joinToString(".")
                if (classPackageName != importedPackage) {
                    node.importReference?.let {
                        context.report(ISSUE, node, context.getLocation(it), SHORT_MESSAGE)
                    }
                }
            }
        }
    }


    companion object {
        val ISSUE = Issue.create(
            "WrongBuildConfigImport",
            "BuildConfig in wrong module",
            "Importing BuildConfig from wrong module is not allowed",
            Category.CORRECTNESS,
            5,
            Severity.ERROR,
            Implementation(
                WrongBuildConfigImportDetector::class.java,
                Scope.JAVA_FILE_SCOPE
            )
        )
        private const val SHORT_MESSAGE = "Invalid Import: Importing BuildConfig from wrong module is not allowed."
    }

}
