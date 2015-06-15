package edu.zipcloud.cloudstreetmarket.core.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.apache.commons.lang3.time.DateUtils;
import org.springframework.social.yahoo.module.ChartHistoMovingAverage;
import org.springframework.social.yahoo.module.ChartHistoSize;
import org.springframework.social.yahoo.module.ChartHistoTimeSpan;
import org.springframework.social.yahoo.module.ChartType;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "ticker_type")
@Table(name="chart")
public abstract class Chart extends AbstractAutogeneratedId<Long> {
	
	@Enumerated(EnumType.STRING)
	@Column(name="type")
	private ChartType type;
	
	@Enumerated(EnumType.STRING)
	@Column(name="time_span")
	private ChartHistoTimeSpan histoTimeSpan;
	
	@Enumerated(EnumType.STRING)
	@Column(name="moving_average")
	private ChartHistoMovingAverage histoMovingAverage;
	
	@Enumerated(EnumType.STRING)
	@Column(name="size")
	private ChartHistoSize histoSize;
	
	@Column(name="width")
	private Integer intradayWidth;
	
	@Column(name="height")
	private Integer intradayHeight;
	
	@Column(name="internal_path")
	private String internalPath;

	@Column(name="last_update", insertable=false, columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	@Temporal(TemporalType.TIMESTAMP)
	private Date lastUpdate;	

	private String path;
	
	public Chart(){
		
	}
	
	public Chart(ChartType type, ChartHistoSize histoSize, ChartHistoMovingAverage histoAverage,
			ChartHistoTimeSpan histoPeriod, Integer intradayWidth, Integer intradayHeight, String path) {
		this.type = type;
		this.histoSize = histoSize;
		this.histoMovingAverage = histoAverage;
		this.histoTimeSpan = histoPeriod;
		this.intradayWidth = intradayWidth;
		this.intradayHeight = intradayHeight;
		this.path = path;
	}

	public ChartType getType() {
		return type;
	}

	public void setType(ChartType type) {
		this.type = type;
	}

	public ChartHistoTimeSpan getHistoTimeSpan() {
		return histoTimeSpan;
	}

	public void setHistoTimeSpan(ChartHistoTimeSpan histoTimeSpan) {
		this.histoTimeSpan = histoTimeSpan;
	}

	public ChartHistoMovingAverage getHistoMovingAverage() {
		return histoMovingAverage;
	}

	public void setHistoMovingAverage(ChartHistoMovingAverage histoMovingAverage) {
		this.histoMovingAverage = histoMovingAverage;
	}

	public ChartHistoSize getHistoSize() {
		return histoSize;
	}

	public void setHistoSize(ChartHistoSize histoSize) {
		this.histoSize = histoSize;
	}

	public Integer getIntradayWidth() {
		return intradayWidth;
	}

	public void setIntradayWidth(Integer intradayWidth) {
		this.intradayWidth = intradayWidth;
	}

	public Integer getIntradayHeight() {
		return intradayHeight;
	}

	public void setIntradayHeight(Integer intradayHeight) {
		this.intradayHeight = intradayHeight;
	}

	public String getInternalPath() {
		return internalPath;
	}

	public void setInternalPath(String internalPath) {
		this.internalPath = internalPath;
	}

	public Date getLastUpdate() {
		return lastUpdate;
	}

	public void setLastUpdate(Date lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public boolean isExpired(int ttlInMinutes){
		return DateUtils.addMinutes(lastUpdate, ttlInMinutes).after(new Date());
	}

	//Avoid fetching lazy collections at this stage (session may be closed)
	@Override
	public String toString() {
		return "Chart [type=" + type + ", histoTimeSpan=" + histoTimeSpan
				+ ", histoMovingAverage=" + histoMovingAverage + ", histoSize="
				+ histoSize + ", intradayWidth=" + intradayWidth
				+ ", intradayHeight=" + intradayHeight + ", internalPath="
				+ internalPath + ", lastUpdate=" + lastUpdate + ", path="
				+ path + ", id=" + id + "]";
	}
}
