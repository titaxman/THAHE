package istia.st.springmvc.models;

import java.util.Date;

import javax.validation.constraints.AssertFalse;
import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Future;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;
import org.hibernate.validator.constraints.URL;
import org.springframework.format.annotation.DateTimeFormat;

public class Form01 {

	// valeurs postées
	@NotNull
	@AssertFalse
	private Boolean assertFalse;
	
	@NotNull
	@AssertTrue
	private Boolean assertTrue;
	
	@NotNull
	@Future
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date dateInFuture;
	
	@NotNull
	@Past
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date dateInPast;
	
	@NotNull
	@Max(value = 100)
	private Integer intMax100;
	
	@NotNull
	@Min(value = 10)
	private Integer intMin10;
	
	@NotNull
	@NotBlank
	private String strNotEmpty;
	
	@NotNull
	@Size(min = 4, max = 6)
	private String strBetween4and6;
	
	@NotNull
	@Pattern(regexp = "^\\d{2}:\\d{2}:\\d{2}$")
	private String hhmmss;
	
	@NotNull
	@Email
	@NotBlank
	private String email;
	
	@NotNull
	@Length(max = 4, min = 4)
	private String str4;
	
	@Range(min = 10, max = 14)
	@NotNull
	private Integer int1014;
	
	@NotNull
	@DecimalMax(value = "3.4")
	@DecimalMin(value = "2.3")
	private Double double1;
	
	@NotNull
	private Double double2;
	
	@NotNull
	private Double double3;
	
	@URL
	@NotBlank
	private String url;
	
	// validation client
	private boolean clientValidation = true;
	// locale
	private String lang;
	
	// getters et setters

	public Boolean getAssertFalse() {
		return assertFalse;
	}

	public void setAssertFalse(Boolean assertFalse) {
		this.assertFalse = assertFalse;
	}

	public Boolean getAssertTrue() {
		return assertTrue;
	}

	public void setAssertTrue(Boolean assertTrue) {
		this.assertTrue = assertTrue;
	}

	public Date getDateInFuture() {
		return dateInFuture;
	}

	public void setDateInFuture(Date dateInFuture) {
		this.dateInFuture = dateInFuture;
	}

	public Date getDateInPast() {
		return dateInPast;
	}

	public void setDateInPast(Date dateInPast) {
		this.dateInPast = dateInPast;
	}

	public Integer getIntMax100() {
		return intMax100;
	}

	public void setIntMax100(Integer intMax100) {
		this.intMax100 = intMax100;
	}

	public Integer getIntMin10() {
		return intMin10;
	}

	public void setIntMin10(Integer intMin10) {
		this.intMin10 = intMin10;
	}

	public String getStrBetween4and6() {
		return strBetween4and6;
	}

	public void setStrBetween4and6(String strBetween4and6) {
		this.strBetween4and6 = strBetween4and6;
	}

	public String getHhmmss() {
		return hhmmss;
	}

	public void setHhmmss(String hhmmss) {
		this.hhmmss = hhmmss;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getStr4() {
		return str4;
	}

	public void setStr4(String str4) {
		this.str4 = str4;
	}

	public Integer getInt1014() {
		return int1014;
	}

	public void setInt1014(Integer int1014) {
		this.int1014 = int1014;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getStrNotEmpty() {
		return strNotEmpty;
	}

	public void setStrNotEmpty(String strNotEmpty) {
		this.strNotEmpty = strNotEmpty;
	}

	public Double getDouble1() {
		return double1;
	}

	public void setDouble1(Double double1) {
		this.double1 = double1;
	}

	public Double getDouble2() {
		return double2;
	}

	public void setDouble2(Double double2) {
		this.double2 = double2;
	}

	public Double getDouble3() {
		return double3;
	}

	public void setDouble3(Double double3) {
		this.double3 = double3;
	}

	public boolean isClientValidation() {
		return clientValidation;
	}

	public void setClientValidation(boolean clientValidation) {
		this.clientValidation = clientValidation;
	}

	public String getLang() {
		return lang;
	}

	public void setLang(String lang) {
		this.lang = lang;
	}


}
