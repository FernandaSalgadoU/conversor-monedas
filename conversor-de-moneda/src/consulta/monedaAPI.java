package consulta;

public record monedaAPI(String base_code,
                        String target_code,
                        double conversion_result) {

    public monedaAPI (String base_code,
                      String target_code,
                      double conversion_result){
        this.base_code=base_code;
        this.target_code=target_code;
        this.conversion_result=conversion_result;
    }

    public String base_code() {
        return this.base_code;
    }

    public String target_code() {
        return this.target_code;
    }

    public double conversion_result() {
        return this.conversion_result;
    }

}
