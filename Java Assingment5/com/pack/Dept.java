package com.pack;

public class Dept {
    private int depId;
    private String depName;

    // Getters and Setters
    public int getDepId() {
        return depId;
    }

    public void setDepId(int depId) {
        this.depId = depId;
    }

    public void setDepName(String depName) {
        this.depName = depName;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + depId;
        result = prime * result + ((depName == null) ? 0 : depName.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        Dept other = (Dept) obj;
        if (depId != other.depId)
            return false;
        if (depName == null) {
            if (other.depName != null)
                return false;
        } else if (!depName.equals(other.depName))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Dept{" +
                "depId=" + depId +
                ", depName='" + depName + '\'' +
                '}';
    }
}
