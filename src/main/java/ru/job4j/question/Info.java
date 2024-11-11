package ru.job4j.question;

public class Info {

    private int added;
    private int changed;
    private int deleted;

    public Info(int added, int changed, int deleted) {
        this.added = added;
        this.changed = changed;
        this.deleted = deleted;
    }

    public int getAdded() {
        return added;
    }

    public int getChanged() {
        return changed;
    }

    public int getDeleted() {
        return deleted;
    }

    public void setAdded(int added) {
        this.added = added;
    }

    public void setChanged(int changed) {
        this.changed = changed;
    }

    public void setDeleted(int deleted) {
        this.deleted = deleted;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + added;
        result = prime * result + changed;
        result = prime * result + deleted;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        }
        Info info = (Info) obj;
        return (added == info.added) && (changed == info.changed) && (deleted == info.deleted);
    }
}
