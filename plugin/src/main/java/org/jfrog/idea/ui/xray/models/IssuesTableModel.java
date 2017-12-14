package org.jfrog.idea.ui.xray.models;

import com.google.common.collect.Sets;
import org.apache.commons.lang.StringUtils;
import org.jetbrains.annotations.NotNull;
import org.jfrog.idea.xray.persistency.types.Issue;

import javax.swing.table.AbstractTableModel;
import java.util.Set;

/**
 * Created by Yahav Itzhak on 13 Nov 2017.
 */
public class IssuesTableModel extends AbstractTableModel {

    private Set<Issue> issues;

    public IssuesTableModel() {
        this(Sets.newHashSet());
    }

    public IssuesTableModel(@NotNull Set<Issue> issues) {
        this.issues = issues;
    }

    public enum IssueColumn {
        SEVERITY("Severity"),
        SUMMARY("Summary"),
        ISSUE_TYPE("Issue Type"),
        COMPONENT("Component");

        private String name;
        IssueColumn(String name) {
            this.name = name;
        }
        public String getName() {
            return this.name;
        }
    }

    @Override
    public int getColumnCount() {
        return IssueColumn.values().length;
    }

    @Override
    public int getRowCount() {
        return issues.size();
    }

    @Override
    public String getColumnName(int col) {
        return IssueColumn.values()[col].getName();
    }

    @Override
    public Object getValueAt(int row, int col) {
        IssueColumn issueColumn = IssueColumn.valueOf(IssueColumn.values()[col].toString());
        Issue issue = (Issue) issues.toArray()[row];
        switch (issueColumn) {
            case SEVERITY: return issue.severity;
            case SUMMARY: return issue.summary;
            case ISSUE_TYPE: return StringUtils.capitalize(issue.issueType);
            case COMPONENT: return issue.component;
        }
        return "N/A";
    }
}