package com.demo.time2.DTO;

import com.demo.time2.domain.ResultDuration;

public class ResultDurationDTO {
    ResultDuration resultDuration;
    String date;

    public ResultDurationDTO(ResultDuration resultDuration, String date) {
        this.resultDuration = resultDuration;
        this.date = date;
    }

    public ResultDuration getResultDuration() {
        return resultDuration;
    }

    public void setResultDuration(ResultDuration resultDuration) {
        this.resultDuration = resultDuration;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ResultDurationDTO that = (ResultDurationDTO) o;

        if (!resultDuration.equals(that.resultDuration)) {
            return false;
        }
        return date.equals(that.date);
    }

    @Override
    public int hashCode() {
        int result = resultDuration.hashCode();
        result = 31 * result + date.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "ResultDurationDTO{" +
            "resultDuration=" + resultDuration +
            ", date='" + date + '\'' +
            '}';
    }
}
