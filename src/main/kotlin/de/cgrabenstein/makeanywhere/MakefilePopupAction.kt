package de.cgrabenstein.makeanywhere

import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.DefaultActionGroup
import com.intellij.openapi.ui.popup.JBPopupFactory
import com.jetbrains.lang.makefile.MakefileRunTargetAction
import com.jetbrains.lang.makefile.MakefileTargetIndex

class MakefilePopupAction : AnAction() {
    override fun actionPerformed(actionEvent: AnActionEvent) {
        val project = actionEvent.project ?: return

        val makefileTargets = MakefileTargetIndex.getInstance().allTargets(project)

        val actionGroup = DefaultActionGroup()
        actionGroup.addAll(makefileTargets.map { MakefileRunTargetAction(it) })

        JBPopupFactory
                .getInstance()
                .createActionGroupPopup("Make actions", actionGroup, actionEvent.dataContext, JBPopupFactory.ActionSelectionAid.SPEEDSEARCH, true)
                .showCenteredInCurrentWindow(project)
    }
}