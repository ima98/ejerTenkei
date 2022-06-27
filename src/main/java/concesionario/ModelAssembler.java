package concesionario;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

@Component
class ModelAssembler implements RepresentationModelAssembler<Coche, EntityModel<Coche>> {

  @Override
  public EntityModel<Coche> toModel(Coche coche) {

    return EntityModel.of(coche, //
        linkTo(methodOn(Controlador.class).all()).withRel("cohes"));
  }
}